// Kannada PWA Service Worker
// Version: 1.0.0
// Purpose: Enable offline functionality and caching

const CACHE_NAME = 'kannada-pwa-v1.0.0';
const STATIC_CACHE = 'kannada-pwa-static-v1.0.0';
const DYNAMIC_CACHE = 'kannada-pwa-dynamic-v1.0.0';

// Files to cache for offline functionality
const STATIC_FILES = [
  '/',
  '/index.html',
  '/KannadaPWA.js',
  '/manifest.json',
  '/sw.js'
];

// Install event - cache static files
self.addEventListener('install', (event) => {
  console.log('🚀 Kannada PWA Service Worker: Installing...');
  
  event.waitUntil(
    caches.open(STATIC_CACHE)
      .then((cache) => {
        console.log('📦 Caching static files for offline use');
        return cache.addAll(STATIC_FILES);
      })
      .then(() => {
        console.log('✅ Static files cached successfully');
        return self.skipWaiting();
      })
      .catch((error) => {
        console.error('❌ Error caching static files:', error);
      })
  );
});

// Activate event - clean up old caches
self.addEventListener('activate', (event) => {
  console.log('🔄 Kannada PWA Service Worker: Activating...');
  
  event.waitUntil(
    caches.keys()
      .then((cacheNames) => {
        return Promise.all(
          cacheNames.map((cacheName) => {
            if (cacheName !== STATIC_CACHE && cacheName !== DYNAMIC_CACHE) {
              console.log('🗑️ Deleting old cache:', cacheName);
              return caches.delete(cacheName);
            }
          })
        );
      })
      .then(() => {
        console.log('✅ Service Worker activated successfully');
        return self.clients.claim();
      })
  );
});

// Fetch event - serve from cache when offline
self.addEventListener('fetch', (event) => {
  const { request } = event;
  const url = new URL(request.url);
  
  // Skip non-GET requests
  if (request.method !== 'GET') {
    return;
  }
  
  // Handle different types of requests
  if (url.pathname === '/' || url.pathname === '/index.html') {
    // Main page - serve from cache first, then network
    event.respondWith(
      caches.match(request)
        .then((response) => {
          if (response) {
            console.log('📱 Serving main page from cache');
            return response;
          }
          return fetch(request)
            .then((networkResponse) => {
              // Cache the network response for future offline use
              const responseClone = networkResponse.clone();
              caches.open(DYNAMIC_CACHE)
                .then((cache) => cache.put(request, responseClone));
              return networkResponse;
            });
        })
        .catch(() => {
          // If both cache and network fail, show offline page
          console.log('📱 Serving offline fallback');
          return caches.match('/index.html');
        })
    );
  } else if (url.pathname.endsWith('.js') || url.pathname.endsWith('.json')) {
    // JavaScript and JSON files - cache first strategy
    event.respondWith(
      caches.match(request)
        .then((response) => {
          if (response) {
            console.log('📦 Serving asset from cache:', url.pathname);
            return response;
          }
          return fetch(request)
            .then((networkResponse) => {
              // Cache for offline use
              const responseClone = networkResponse.clone();
              caches.open(STATIC_CACHE)
                .then((cache) => cache.put(request, responseClone));
              return networkResponse;
            });
        })
        .catch(() => {
          console.log('❌ Asset not available offline:', url.pathname);
          return new Response('Offline - Asset not available', {
            status: 503,
            statusText: 'Service Unavailable'
          });
        })
    );
  } else {
    // Other requests - network first, fallback to cache
    event.respondWith(
      fetch(request)
        .then((response) => {
          // Cache successful responses
          if (response.status === 200) {
            const responseClone = response.clone();
            caches.open(DYNAMIC_CACHE)
              .then((cache) => cache.put(request, responseClone));
          }
          return response;
        })
        .catch(() => {
          // Try to serve from cache as fallback
          return caches.match(request)
            .then((cachedResponse) => {
              if (cachedResponse) {
                console.log('📦 Serving from cache as fallback:', url.pathname);
                return cachedResponse;
              }
              // Return offline message if nothing in cache
              return new Response('Offline - Content not available', {
                status: 503,
                statusText: 'Service Unavailable'
              });
            });
        })
    );
  }
});

// Background sync for offline actions
self.addEventListener('sync', (event) => {
  console.log('🔄 Background sync triggered:', event.tag);
  
  if (event.tag === 'background-sync') {
    event.waitUntil(
      // Handle any pending offline actions
      console.log('📱 Processing background sync...')
    );
  }
});

// Push notification handling
self.addEventListener('push', (event) => {
  console.log('🔔 Push notification received');
  
  const options = {
    body: event.data ? event.data.text() : 'New content available in Kannada PWA!',
    icon: '/manifest.json',
    badge: '/manifest.json',
    vibrate: [100, 50, 100],
    data: {
      dateOfArrival: Date.now(),
      primaryKey: 1
    },
    actions: [
      {
        action: 'explore',
        title: 'Learn Now',
        icon: '/manifest.json'
      },
      {
        action: 'close',
        title: 'Close',
        icon: '/manifest.json'
      }
    ]
  };
  
  event.waitUntil(
    self.registration.showNotification('Kannada PWA', options)
  );
});

// Notification click handling
self.addEventListener('notificationclick', (event) => {
  console.log('👆 Notification clicked:', event.action);
  
  event.notification.close();
  
  if (event.action === 'explore') {
    event.waitUntil(
      clients.openWindow('/')
    );
  }
});

// Message handling from main thread
self.addEventListener('message', (event) => {
  console.log('📨 Message received from main thread:', event.data);
  
  if (event.data && event.data.type === 'SKIP_WAITING') {
    self.skipWaiting();
  }
  
  if (event.data && event.data.type === 'GET_VERSION') {
    event.ports[0].postMessage({
      version: '1.0.0',
      cacheName: CACHE_NAME
    });
  }
});

console.log('🚀 Kannada PWA Service Worker loaded successfully!');
